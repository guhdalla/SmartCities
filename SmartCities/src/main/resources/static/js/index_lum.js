let arraySensor = [];
let arrayDado = [];
 $('document').ready(function() {
     $.ajax({
         type: "GET",
         url: "http://localhost:8080/luminosidades",
         dataType: "json",
         success: function(data) {
            
	             for (var i = 0; i < data["_embedded"].luminosidades.length; i++) {
                      arraySensor.push(data["_embedded"].luminosidades[i].luminosidade);
                      arrayDado.push(data["_embedded"].luminosidades[i].datasinal);
  
            }                   
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
                label: "LUX",
                backgroundColor: '#aa78fd',
                borderColor: [
                    '#aa78fd',
                    '#aa78fd',
                    '#aa78fd',
                    '#aa78fd',
                    '#aa78fd',
                    '#aa78fd',
                    '#aa78fd'
                ],
                data: arraySensor
            }]
        },

        options: {
            plugins: {
                title: {
                    display: true,
                    text: 'TAXA DE LUMINOSIDADE EM LUX',
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