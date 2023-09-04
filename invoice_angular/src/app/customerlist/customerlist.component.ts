import { Component, OnInit } from '@angular/core';
import { Customer } from '../customer';
import { InvoiceService } from '../invoice.service';

@Component({
  selector: 'app-customerlist',
  templateUrl: './customerlist.component.html',
  styleUrls: ['./customerlist.component.css']
})
export class CustomerlistComponent implements OnInit{
  customers: Customer[] = [];
  constructor(private invService:InvoiceService){}
  ngOnInit(): void {
  this.invService.getAllCustomer().subscribe(
    (customers) => (this.customers = customers),
    (error) => console.error('Error fetching customers:', error)
  );
  }
}
