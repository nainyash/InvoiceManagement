import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Customer, Items } from './customer';
import { Invoice } from './customer';

@Injectable({
  providedIn: 'root'
})
export class InvoiceService {
  private baseUrl = 'http://localhost:8080/customer';

  constructor(private http: HttpClient) { }

  getAllCustomer(): Observable<Customer[]> {
    return this.http.get<Customer[]>(`${this.baseUrl}/getcust`);
  }
  submitFormData(formData: any, headers: HttpHeaders): Observable<any> {
    return this.http.post(`${this.baseUrl}/addcust`, formData, { headers });
  }
  addItem(itemData: any): Observable<any> {
    const url = `${this.baseUrl}/additem`;
    return this.http.post(url, itemData);
  }
  checkIfItemExists(name: string): Observable<boolean> {
    const url = `${this.baseUrl}/check-existence?name=${name}`;
    return this.http.get<boolean>(url);
  }
  getAllItem(): Observable<Items[]> {
    return this.http.get<Items[]>(`${this.baseUrl}/getitem`);
  }
  saveInvoiceData(data: any,): Observable<any> {
    return this.http.post(`${this.baseUrl}/addinvoices`, data);
  }
  GetInvoiceData(): Observable<any> {
    return this.http.get(`${this.baseUrl}/getinvoices`);
  }

  deleteInvoice(invoiceid: number): Observable<void> {
    const url = `${this.baseUrl}/deleteInvoice/${invoiceid}`;
    return this.http.delete<void>(url);
  }
  updateInvoice(invoiceId: number, updatedInvoice: any): Observable<any> {
    return this.http.put(`${this.baseUrl}/updateinvoice/${invoiceId}`, updatedInvoice);
  }
  getInvoiceById(invoiceId: string | null) {
    return this.http.get(`${this.baseUrl}/invoicebyid/${invoiceId}`);
  }
}
