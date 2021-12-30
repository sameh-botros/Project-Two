import { ActivatedRoute } from '@angular/router';
import { Component, OnInit } from '@angular/core';
import { ReimbursmnetService } from '../reimbursmnet.service';
import { RequestModel } from '../request.model';
import { Router } from '@angular/router';
import { DomSanitizer } from '@angular/platform-browser';


@Component({ selector: 'app-spacific-display', templateUrl: './spacific-display.component.html', styleUrls: ['./spacific-display.component.css'] })
export class SpacificDisplayComponent implements OnInit {
  errorMsg: string = "";
  newRequest: RequestModel[] = [];
  constructor(private reimbursmnetService: ReimbursmnetService, private activatedRoute: ActivatedRoute, private router: Router, private sanitizer: DomSanitizer) { }


  ngOnInit(): void {
    var userid: any = this.activatedRoute.snapshot.paramMap.get("sentUserid");
    this.reimbursmnetService.SpacificEmployeeService(userid).subscribe((response) => {
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
