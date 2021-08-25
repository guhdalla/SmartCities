let arraySensor = [];
let arrayDado = [];
 $('document').ready(function() {
     $.ajax({
         type: "GET",
         url: "http://localhost:8080/temperaturas",
         dataType: "json",
         success: function(data) {
            
	             for (var i = 0; i < 8; i++) {
                      arraySensor.push(data["_embedded"].temperaturas[i].temperatura);
                      arrayDado.push(data["_embedded"].temperaturas[i].datasinal);
                      if(data["_embedded"].temperaturas[i].datasinal != null){
                          i= -1
                      }

                      
                     
            }                   
             grafico(arrayDado, arraySensor)
        }
    })

 })

function grafico(arrayDado, arraySensor){
    let ctx = document.getElementById('chart').getContext('2d');

    let chart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: arrayDado,
            datasets: [{
                label: "custo por X",
                backgroundColor: 'rgb(255,99,132)',
                borderColor: [
                    'rgb(255,99,132)',
                    'rgb(255,99,132)',
                    'rgb(255,99,132)',
                    'rgb(255,99,132)',
                    'rgb(255,99,132)',
                    'rgb(255,99,132)',
                    'rgb(255,99,132)'
                ],
                data: arraySensor
            }]
        },

        options: {
            plugins: {
                title: {
                    display: true,
                    text: 'CAC - CUSTO DE AQUISIÇÃO DE CLIENTE',
                },
                legend: {
                    position: 'bottom'
                }
            },

            animation: {
                duration: 3000,
                easing: 'easeOutBounce'
            },

            layout: {
                padding: {
                    left: 20,
                    right: 20,
                    bottom: 20,
                    top: 20
                }
            },
            scales: {
                x: {
                    grid: {
                        display: true, // nao aparece linha do eixo X
                        borderColor: 'red',
                        drawBorder: false
                    }
                },
                y: {
                    grid: {
                        display: true, // nao aparece linha do eixo X
                        borderColor: 'red',
                        drawBorder: false
                    }
                }
            }
        }
    })
}