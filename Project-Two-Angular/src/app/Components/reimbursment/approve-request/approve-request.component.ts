import { NgForm } from '@angular/forms';
import { ReimbursmnetService } from './../reimbursmnet.service';
import { Component, OnInit } from '@angular/core';
import { RequestModel } from '../request.model';
import { MatDialog, MatDialogConfig } from '@angular/material/dialog';
import { ShowReceiptComponent } from '../show-receipt/show-receipt.component';
import { Router } from '@angular/router';
import { UserModel } from '../users.model';
// DomSanitizer to prevent bypass security Trusst Url
import { DomSanitizer, SafeUrl } from '@angular/platform-browser'


@Component({ selector: 'app-approve-request', templateUrl: './approve-request.component.html', styleUrls: ['./approve-request.component.css'] })
export class ApproveRequestComponent implements OnInit {

  newRequest: RequestModel[] = [];
  requestForm: RequestModel = new RequestModel();
  newUser: UserModel = new UserModel();
  previews: string[] = [];

  errorMsg: string = "";
  dateNow: Date = new Date();
  lccaldate: string = new Date().toLocaleString();
  tustMyUrl: SafeUrl | undefined;

  constructor(private reimburementService: ReimbursmnetService,
    private dialog: MatDialog, private router: Router, private sanitizer: DomSanitizer) { }

  ngOnInit(): void {
    this.viewallPendingRequest();
  }

  // approveRequest(requestId: number) {
  approveRequest(requestId: number, usersId: number, flag: number) {
    this.reimburementService.approveRequestService(requestId).subscribe((response) => {
      this.requestForm = response;
      this.viewallPendingRequest();
    }, (error) => {
      this.errorMsg = 'There was some internal error! Please try again later!';
    })
    this.reimburementService.sendEmailService(usersId, flag).subscribe((response) => {
    }, (error) => {
      this.errorMsg = 'There was some internal error in Email ! Please try again later!';
    })
  }

  RejectRequest(requestId: number, usersId: number, flag: number) {

    this.reimburementService.rejectRequestService(requestId).subscribe((response) => {
      this.requestForm = response;
      this.viewallPendingRequest();
      this.reimburementService.sendEmailService(usersId, flag).subscribe((response) => {
      }, (error) => {
        this.errorMsg = 'Your message was not delivered because the email addres is not correct ';
      })

    }, (error) => {
      this.errorMsg = 'There was some internal error! Please try again later!';
    })

  }

  viewallPendingRequest() {
    this.reimburementService.allPendingRequstsService().subscribe((response) => {
      this.newRequest = response;
      this.newRequest.forEach((request, index) => {
        const mediaType = 'application/image';
        this.reimburementService.getFilesByRb(request.id).subscribe(value => {
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



  largeImg(myimg: { src: any; }) {
    //this.router.navigate(['/show_receipt', this.requestForm.img]);
    this.tustMyUrl = this.sanitizer.bypassSecurityTrustUrl(myimg.src)
    const dialogConfig = new MatDialogConfig();
    this.dialog.open(ShowReceiptComponent, {
      width: "40%",
      data: { img: this.tustMyUrl },
      disableClose: true,
      autoFocus: true
    });

  }


}
