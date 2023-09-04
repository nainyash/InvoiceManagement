import { Component } from '@angular/core';
import { InvoiceService } from '../invoice.service';

@Component({
  selector: 'app-dash',
  templateUrl: './dash.component.html',
  styleUrls: ['./dash.component.css']
})
export class DashComponent {
  constructor(private service: InvoiceService) { }
  data: any[] = [];

  ngOnInit(): void {
    this.LoadData();
  }
  // to get invoice data from databse
  LoadData(): void {
    this.service.GetInvoiceData().subscribe(
      (data) => {
        this.data = data;
      },
      (error) => {
        console.error('Error fetching data:', error);
      }
    );
  }

  // to delete invoice data from databse

  deleteInvoice(invoiceid: number): void {
    this.service.deleteInvoice(invoiceid).subscribe(
      () => {
        this.LoadData();
      },
      (error) => {
        console.error('Error deleting invoice:', error);
      }
    );
    location.reload();
  }

}
