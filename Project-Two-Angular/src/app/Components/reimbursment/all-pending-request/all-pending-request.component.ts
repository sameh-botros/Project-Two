import { Component, OnInit, NgModule, TemplateRef } from '@angular/core';
import { ReimbursmnetService } from '../reimbursmnet.service';
import { RequestModel } from '../request.model';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';



@Component({ selector: 'app-all-pending-request', templateUrl: './all-pending-request.component.html', styleUrls: ['./all-pending-request.component.css'] })
export class AllPendingRequestComponent implements OnInit {
  fileInfos?: any;

  previews: string[] = [];

  newRequest: RequestModel[] = [];
  temp: RequestModel = new RequestModel();
  errorMsg: string = "";
  constructor(private reimbursmnetService: ReimbursmnetService, private router: Router,private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.viewallPendingRequest();
  }

  viewallPendingRequest() {
    this.reimbursmnetService.allPendingRequstsService().subscribe((response) => {
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
  showimg(imgReq: RequestModel) {
    this.router.navigate(['/user-specific-Display', imgReq]);

  }
}
