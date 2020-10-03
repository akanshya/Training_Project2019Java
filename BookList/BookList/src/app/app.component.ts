import { Component, OnInit } from '@angular/core';
import { Book } from './book.model';
import { BookService } from './book.service';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit {
  books:Book[];
  filteredBooks:Book[];
  searchFlag=false;

  constructor(private svc: BookService) { }

  ngOnInit() {
    this.svc.getBooks()
        .subscribe(
            result => {
                this.books=result;
                this.filteredBooks=result;
            },
            error => console.log(error)
        )
}

search(searchfield, columnName){
    this.searchFlag = true;
  let searchText= searchfield.value;
  this.filteredBooks=this.books.filter((item,index,books)=>{
      return item[columnName].toString()
      .toLowerCase().indexOf(searchText.toLowerCase()) >=0;
  })
}

add(id, title, author, publishYear) {
    this.books.push({ id: id.value, title: title.value, 
        author: author.value, publishYear: publishYear.value });
        var x=document.getElementById("msg");
        x.innerHTML+="Data inserted"+"<br>";
    }

    delete(bid) {
        let index = this.books.findIndex(sample => sample.id === bid);
        this.books.splice(index, 1);
        
      }
    
      sort(columnName) {
        this.books.sort((a, b) => {
          if (a[columnName] > b[columnName]) {
            return 1;
          } else if (a[columnName] < b[columnName]) {
            return -1;
          }
          return 0;
        })
      }
    
      editItem:Book = {
        id: 0,
        title:"",
        author:"",
        publishYear:0 
      };
    
      edit(bid){
        this.editItem = this.books.find(sample => sample.id === bid);
      }
    
      update(bid, btitle, bauthor, bpublishYear) {
        let item = this.books.find(sample => sample.id === parseInt(bid.value));
        item["id"] = bid.value;
        item["title"] = btitle.value;
        item["author"] = bauthor.value;
        item["publishYear"] = bpublishYear.value;
        this.editItem = new Book();
        this.editItem = {
          id: 0,
          title:"",
          author:"",
          publishYear:0
        }
      }








}
