export interface Customer {
  id: number;
  customerName: string;
  mail: string;
  mobile: number;
  password: string;
}

export interface Items {
  id: number;
  name: string;
  itemprice: number;
}

export interface Invoice {
  id: number;
  customerName: string;
  invoiceNumber: string;
  curDate: string;
  item: Item[];
}


export interface Item {
  id: number;
  itemName: string;
  price: number;
  quantity: number;
  subtotal: number;
  gstAmount: number;
  totalAmount: number;
  invoice: {
    id:number;
  };
}
