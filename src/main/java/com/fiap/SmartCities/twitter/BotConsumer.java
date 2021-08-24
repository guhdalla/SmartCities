package com.fiap.SmartCities.twitter;

import com.fiap.SmartCities.util.ResourceConfigBot;
import com.github.redouane59.twitter.TwitterClient;
import com.github.redouane59.twitter.dto.tweet.Tweet;
import com.github.redouane59.twitter.signature.TwitterCredentials;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.math.BigDecimal;
import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Files;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BotConsumer {
    private static final int INTERVALO_MINIMO_HORAS = 48;
    private static final boolean POSTAR_TWEET_IGUAL = false;
    private static final boolean POSTAR_TWEET = true;

    private static final Logger LOG = LoggerFactory.getLogger(BotConsumer.class);

    private static final String JSON_CREDENCIAIS = "secretToken.json";
    private static final TwitterCredentials TWITTER_CREDENTIALS;
    private static final TwitterClient TWITTER_CLIENT;

    private static final Map<Integer, String> VELOCIDADES = new HashMap<Integer, String>();
    private static final Duration INTERVALO_MINIMO = Duration.ofHours(INTERVALO_MINIMO_HORAS).minusMinutes(9);

    static {
        try {
            URL json = ResourceConfigBot.getResource(JSON_CREDENCIAIS, BotConsumer.class);

            TWITTER_CREDENTIALS = TwitterClient.OBJECT_MAPPER.readValue(json, TwitterCredentials.class);
            TWITTER_CLIENT = new TwitterClient(TWITTER_CREDENTIALS);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        /* TODO: Colocar aqui toda regra utilizando o MQTT
        * Criar um laço de repetição WHILE que contém variavel A que é sempre igual a true
        * Criar um se para verificar se a velocidade do vento passou do limite, se sim ele sai do laço e faz o tweet
         */
        VELOCIDADES.put(1, "40");

    }

    public static void executar() throws IOException {
        Instant agora = Instant.now();
        LOG.debug("Começando.");

        LOG.debug("Postando para a conta {}.", TWITTER_CLIENT.getUserFromUserId(TWITTER_CLIENT.getUserIdFromAccessToken()).getName());

        Tweet dadosUltimoTweet = ultimoTweet();
        if (!passouIntervaloMinimo(dadosUltimoTweet)) {
            LOG.info("Finalizou porque não passou o intervalo mínimo desde o último tweet.");
            return;
        }

        String novoTweet = montarNovoTweet(); // ALTERE O INTERIOR DESTE MÉTODO!

        if (!POSTAR_TWEET_IGUAL && numerosNaoMudaramDesdeUltimoTweet(dadosUltimoTweet, novoTweet)) {
            LOG.info("A velocidade já foi tweetada anteriormente.");
            return;
        }
        if (POSTAR_TWEET) {
            LOG.debug("Postando tweet.");
            TWITTER_CLIENT.postTweet(novoTweet);
            LOG.info("Tweet postado.");
        } else {
            LOG.info("Não postou tweet porque está configurado assim.");
        }
        LOG.debug("Acabou em {}ms.", Duration.between(agora, Instant.now()).toMillis());
    }

    private static String montarNovoTweet() throws IOException {
        LOG.debug("Montando novo tweet.");
        StringBuilder sb = new StringBuilder(280);

        for (String string : VELOCIDADES.values()) {
            BigDecimal bigDecimal = new BigDecimal(string);
            sb.append("[‼️] - VELOCIDADE DO VENTO EM NIVEL CRITICO! - [‼️]\n");
            sb.append("[❌] 100% RISCO ACIMA DE 50Km/h! [❌]\n");
            sb.append("[➰] - VELOCIDADE ATUAL-  ").append(string).append("Km/h - [➰]\n");
            sb.append("				").append(converterEmBarras(bigDecimal));
            sb.append(" ").append(100 - (50 - bigDecimal.intValue()));
            sb.append("%\n");
        }
        sb.delete(sb.length() - 1, sb.length());

        String novoTweet = sb.toString();
        LOG.debug("Tweet montado, será apresentado no próximo log.");
        LOG.debug("\n" + novoTweet);
        return novoTweet;
    }

    private static boolean numerosNaoMudaramDesdeUltimoTweet(Tweet dadosUltimoTweet, String novoTweet) {
        LOG.debug("Checando se números mudaram desde o último tweet.");
        String ultimoTweet = dadosUltimoTweet.getText();
        String numerosNovoTweet = novoTweet.replaceAll("[^\\d]", "");
        String numerosUltimoTweet = ultimoTweet.replaceAll("[^\\d]", "");
        boolean contentEquals = numerosNovoTweet.contentEquals(numerosUltimoTweet);
        LOG.debug("Números mudaram? {}. Tweet anterior: {}. Novo tweet: {}.", !contentEquals, numerosUltimoTweet, numerosNovoTweet);
        return contentEquals;
    }

    private static Boolean passouIntervaloMinimo(Tweet dadosUltimoTweet) {
        LOG.debug("Checando intervalo mínimo.");
        LocalDateTime dataUltimoTweet = dadosUltimoTweet.getCreatedAt();
        LocalDateTime agora = LocalDateTime.now();
        Duration between = Duration.between(dataUltimoTweet, agora);
        boolean passouIntervaloMinimo = between.toMinutes() > INTERVALO_MINIMO.toMinutes();
        LOG.debug("Passou intervalo mínimo? {}", passouIntervaloMinimo);
        return passouIntervaloMinimo;
    }

    private static Tweet ultimoTweet() {
        LOG.debug("Lendo último tweet.");
        List<Tweet> userTimeline = TWITTER_CLIENT.getUserTimeline(TWITTER_CLIENT.getUserIdFromAccessToken(), 5);
        Tweet dadosUltimoTweet = userTimeline.get(0);
        LOG.debug("Último tweet recuperado. Feito em {}.", dadosUltimoTweet.getCreatedAt());
        return dadosUltimoTweet;
    }

    private static String converterEmBarras(BigDecimal bd) {
        LOG.debug("Convertendo {} em barras.", bd);
        int constante = 15;
        int intValue = bd.intValue() / (50 / constante);
        StringBuilder sb = new StringBuilder(50);
        sb.append("▓".repeat(Math.max(0, intValue)));
        sb.append("░".repeat(Math.max(0, constante - intValue)));
        String string = sb.toString();
        LOG.debug("Convertido em barras. {} virou {}.", bd, string);
        return string;
    }
}
