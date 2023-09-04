import { Component, OnInit } from '@angular/core';
import { InvoiceService } from '../invoice.service';
import {  Items } from '../customer';

@Component({
  selector: 'app-itemlist',
  templateUrl: './itemlist.component.html',
  styleUrls: ['./itemlist.component.css']
})
export class ItemlistComponent implements OnInit {
  items:Items[]=[];
  constructor(private invService:InvoiceService){}
ngOnInit(): void {
  this.invService.getAllItem().subscribe(
    (items) => (this.items = items),
    (error) => console.error('Error fetching items:', error)
  );
}
}
