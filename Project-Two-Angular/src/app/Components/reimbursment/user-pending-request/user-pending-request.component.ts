import { User } from './../../users/users.model';
import { Component, OnInit } from '@angular/core';
import { AuthService } from '../../users/auth.service';
import { ReimbursmnetService } from '../reimbursmnet.service';
import { RequestModel } from '../request.model';
import { UserModel } from '../users.model';
import { DomSanitizer } from '@angular/platform-browser';

@Component({ selector: 'app-user-pending-request', templateUrl: './user-pending-request.component.html', styleUrls: ['./user-pending-request.component.css'] })
export class UserPendingRequestComponent implements OnInit {

  errorMsg: string = "";
  dateNow: Date = new Date();
  lccaldate: string = new Date().toLocaleString();

  constructor(private reimbursmnetService: ReimbursmnetService, private authService: AuthService,  private sanitizer: DomSanitizer) { }
  requestForm: RequestModel[] = [];
  
  ngOnInit(): void {
    var data: User = this.authService.getUser();
    this.viewUserPending(data.id);
  }

  viewUserPending(userId: number) {
    this.reimbursmnetService.UserPendingRequestService(userId).subscribe((response) => {
      this.requestForm = response;
      this.requestForm.forEach((request, index) => {
        const mediaType = 'application/image';
        this.reimbursmnetService.getFilesByRb(request.id).subscribe(value => {
         const blob = new Blob([value], { type: mediaType });
         const unsafeImg = URL.createObjectURL(blob);
         this.requestForm[index].imageBlob = this.sanitizer.bypassSecurityTrustUrl(unsafeImg);
         
       }, error1 => {
        this.requestForm[index].imageBlob = "";
       });
      })
      
      // Sort descending
      this.requestForm.sort((a, b) => a.id - b.id);
    }, (error) => {
      this.errorMsg = 'There was some internal error! Please try again later!';
    })
  }

}
