import {Component, OnInit} from '@angular/core';
import {Chart, LinearScale, LineController, LineElement, PointElement, registerables, Title} from "chart.js";
import {SaleService} from "../service/sale.service";
import {Sale} from "../model/sale";

Chart.register(...registerables)
Chart.register(LineController, LineElement, PointElement, LinearScale, Title);

@Component({
  selector: 'app-testing',
  templateUrl: './testing.component.html',
  styleUrls: ['./testing.component.css']
})
export class TestingComponent implements OnInit {

  lineDatas = [5, 10, 5, 9, 10, 20];
  lineLabels = [1, 2, 3, 4, 5, 6];

  barLabels = ["Vietnam", "China", "USA", "England", "Thailand"];
  barDatas = [55, 49, 44, 24, 15, 0];
  barColors = ["red", "green", "blue", "brown", "orange"];

  pieColors = ["red", "green", "blue", "brown", "orange"];
  pieDatas = [55, 49, 44, 24, 15, 0];
  pieLabels = ["Vietnam", "China", "USA", "England", "Thailand"];

  multiLables = [100, 200, 300, 400, 500, 600, 700, 800, 900, 1000];
  line1 = [860, 1140, 1060, 1060, 1070, 1110, 1330, 2210, 7830, 2478];
  line2 = [1600, 1700, 1700, 1900, 2000, 2700, 4000, 5000, 6000, 7000];
  line3 = [300, 700, 2000, 5000, 6000, 4000, 2000, 1000, 200, 100];

  saleInvoices: string[] = [];
  saleSale: string[] = [];
  saleDates: string[] = [];
  sales: Sale[] = [];

  constructor(private saleService: SaleService) {
    this.saleService.getAll().subscribe(data => {
      this.sales = data;
      for (let s of this.sales) {
        this.saleInvoices.push(s.invoice);
        this.saleSale.push(s.sale);
        let date = new Date(s.date);
        this.saleDates.push("Day : "+date.getDate().toString())
      }
      const saleChart = new Chart("sale",{
        type:"line",
        data:{
          labels:this.saleDates,
          datasets:[{
            label:'Sale',
            data:this.saleSale,
            backgroundColor:"red",
            borderColor:'red'
          }]
        },
        options:{
          plugins:{
            title:{
              display:true,
              text:"Sale Report By Day 01/2020"
            }
          }
        }
      });

      const invoiceChart = new Chart("invoice",{
        type:"line",
        data:{
          labels:this.saleDates,
          datasets:[{
            label:'Invoices',
            data:this.saleInvoices,
            backgroundColor:"blue",
            borderColor:'blue'
          }]
        },
        options:{
          plugins:{
            title:{
              display:true,
              text:"Invoice Report By Day 01/2020"
            }
          }
        }
      });

    })
  }

  ngOnInit(): void {
    let lineChart = new Chart("lineChart", {
      type: "line",
      data: {
        labels: this.lineLabels,
        datasets: [{
          label: "Line Chart",
          data: this.lineDatas,
          backgroundColor: "red",
          tension: 0.5,
          borderColor: 'red'
        }]
      },
      options: {
        plugins: {
          legend: {display: false}
        }
      }
    });
    let barChart = new Chart("barChart", {
      type: "bar",
      data: {
        labels: this.barLabels,
        datasets: [{
          data: this.barDatas,
          backgroundColor: this.barColors
        }]
      },
      options: {
        plugins: {
          legend: {display: false},
          title: {
            display: true,
            text: "BAR CHART"
          }
        }
      }
    });

    let pieChart = new Chart("pieChart", {
      type: "pie",
      data: {
        labels: this.pieLabels,
        datasets: [{
          data: this.pieDatas,
          backgroundColor: this.pieColors
        }]
      },
      options: {
        plugins: {
          title: {
            display: true,
            text: "PIE CHART"
          },
          legend: {display: false}
        }
      }
    });

    let multilineChart = new Chart("multilineChart", {
      type: 'line',
      data: {
        labels: this.multiLables,
        datasets: [{
          label: "RED",
          data: this.line1,
          backgroundColor: 'red',
          borderColor: 'red'
        },
          {
            label: "BLUE",
            data: this.line2,
            backgroundColor: 'blue',
            borderColor: 'blue'
          },
          {
            label: "GREEN",
            data: this.line3,
            backgroundColor: 'green',
            borderColor: 'green'
          }
        ]
      },
      options: {
        plugins: {}
      }
    });

  }

}
