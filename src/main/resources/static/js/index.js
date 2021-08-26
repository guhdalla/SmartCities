let arraySensor = [];
let arrayDado = [];
 $('document').ready(function() {
     $.ajax({
         type: "GET",
         url: "http://localhost:8080/temperaturas",
         dataType: "json",
         success: function(data) {
				
				           


	             for (var i = 0; i < data["_embedded"].temperaturas.length; i++) {
                      arraySensor.push(data["_embedded"].temperaturas[i].temperatura);
                      arrayDado.push(data["_embedded"].temperaturas[i].datasinal);
  
            }                   
			 console.log(data["_embedded"].temperaturas.length);
             grafico(arrayDado, arraySensor)
        }
    })

 })
function grafico(arrayDado,arraySensor){
	
	let ctx = document.getElementById('chart').getContext('2d');
    let chart = new Chart(ctx, {
        type: 'line',
        data: {
            labels: arrayDado,
            datasets: [{
                label: "GRAUS CELSIUS (ºC)",
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
                    text: 'GRÁFICO DE TEMPERATURA - SMARTCITIES',
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