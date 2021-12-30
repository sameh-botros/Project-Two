import { ReimbursmnetService } from './../reimbursmnet.service';
import { Component, OnInit } from '@angular/core';
import { RequestModel } from '../request.model';
import { MatDialog } from '@angular/material/dialog';
import { DomSanitizer } from '@angular/platform-browser';

@Component({ selector: 'app-all-resolved-request', templateUrl: './all-resolved-request.component.html', styleUrls: ['./all-resolved-request.component.css'] })
export class AllResolvedRequestComponent implements OnInit {

  errorMsg: string = "";
  newRequest: RequestModel[] = [];

  constructor(private reimbursmnetService: ReimbursmnetService,private dialog: MatDialog, private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.viewAllResolved();
    
  }

  viewAllResolved() {
    this.reimbursmnetService.AllResolvedRequstService().subscribe((response) => {
      this.newRequest = response;

      this.newRequest.forEach((request, index) => {
        const mediaType = 'application/image';
        this.reimbursmnetService.getFilesByRb(request.id).subscribe(value => {
         const blob = new Blob([value], { type: mediaType });
         const unsafeImg = URL.createObjectURL(blob);
         this.newRequest[index].imageBlob = this.sanitizer.bypassSecurityTrustUrl(unsafeImg);
         
       }, error1 => {
        this.newRequest[index].imageBlob = "";
       });
      })
      
      // Sort descending
      this.newRequest.sort((a, b) => a.id - b.id);
    }, (error) => {
      this.errorMsg = 'There was some internal error! Please try again later!';
    })
  }

}
