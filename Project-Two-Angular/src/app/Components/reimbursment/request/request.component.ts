import { NgForm } from '@angular/forms';
import { ReimbursmnetService } from './../reimbursmnet.service';
import { Component, OnInit } from '@angular/core';
import { RequestModel } from '../request.model';
import { AuthService } from '../../users/auth.service';
import { UserModel } from '../users.model';
import { ActivatedRoute } from '@angular/router';
import { Router } from '@angular/router';
import { HttpEventType, HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs';


@Component({
  selector: 'app-request',
  templateUrl: './request.component.html',
  styleUrls: ['./request.component.css']
})
export class RequestComponent implements OnInit {
  fileInfos?: Observable<any>;
  previews: string[] = [];

  requestForm: RequestModel = new RequestModel();

  flag: boolean = false;
  errorMsg: string = "";


  selectedFiles?: FileList;
  currentFile?: File;
  progress = 0;
  message = '';
  errorMsgimg = '';

  today: Date = new Date();
  dd = String(this.today.getDate()).padStart(2, '0');
  mm = String(this.today.getMonth() + 1).padStart(2, '0'); //January is 0!
  yyyy = this.today.getFullYear();
  dateNow: any = this.yyyy + '/' + this.mm + '/' + this.dd;

  constructor(private reimbursmnetService: ReimbursmnetService, private authService: AuthService, private activatedRoute: ActivatedRoute, private router: Router) { }

  ngOnInit(): void {
    this.fileInfos = this.reimbursmnetService.getFiles();

  }

  addRequest() {
    var data: UserModel = this.authService.getUser();
    this.requestForm.usersId = data.id;
    this.requestForm.reqDate = this.today;
    this.reimbursmnetService.addRequestService(this.requestForm).subscribe((response) => {
      this.requestForm = response;
      this.requestForm.id = response.id;
      this.upload();
      setTimeout(() => {
        this.router.navigate(['/request-message']);
      }, 3000);
    }, (error) => {
      this.errorMsg = 'There was some internal error! Please try again later!';
    })
  }


  selectFile(event: any): void {
    this.selectedFiles = event.target.files;
    this.previews = [];
    if (this.selectedFiles && this.selectedFiles[0]) {
      const numberOfFiles = this.selectedFiles.length;
      for (let i = 0; i < numberOfFiles; i++) {
        const reader = new FileReader();

        reader.onload = (e: any) => {
          this.previews.push(e.target.result);
        };

        reader.readAsDataURL(this.selectedFiles[i]);
      }
    }

  }

  upload(): void {

    this.errorMsgimg = '';
    this.progress = 0;
    this.message = '';

    if (this.selectedFiles) {
      const file: File | null = this.selectedFiles.item(0);

      if (file) {
        this.currentFile = file;

        this.reimbursmnetService.upload(this.currentFile, this.requestForm.id).subscribe(
          (event: any) => {
            if (event.type === HttpEventType.UploadProgress) {
              this.progress = Math.round(100 * event.loaded / event.total);
            } else if (event instanceof HttpResponse) {
              this.message = event.body.responseMessage;

            }
          },
          (err: any) => {
            if (err.error && err.error.responseMessage) {
              this.errorMsgimg = err.error.responseMessage;
            } else {
              this.errorMsgimg = 'Error occurred while uploading a file!';
            }
            this.currentFile = undefined;
          });
      }
      this.selectedFiles = undefined;
    }
  }



}
