import { UserModel } from './../users.model';
import { Component, OnInit } from '@angular/core';
import { ReimbursmnetService } from '../reimbursmnet.service';
import { ValueConverter } from '@angular/compiler/src/render3/view/template';
import { Router } from '@angular/router';
import { RequestModel } from '../request.model';

@Component({ selector: 'app-all-employee', templateUrl: './all-employee.component.html', styleUrls: ['./all-employee.component.css'] })
export class AllEmployeeComponent implements OnInit {

  myRequestModel: RequestModel = new RequestModel();
  newUser: UserModel[] = [];
  errorMsg: string = "";

  constructor(private reimbursmnetService: ReimbursmnetService, private router: Router) { }

  ngOnInit(): void {
    this.viewallEployee();
  }

  viewallEployee() {
    this.reimbursmnetService.allEmployeeService().subscribe((response) => {
      this.newUser = response;
      // Sort descending
      this.newUser.sort((a, b) => a.id - b.id);
      // ascending
      // this.newUser.sort((a, b) => b.id - a.id);
    }, (error) => {
      this.errorMsg = 'There was some internal error! Please try again later!';

    })
  }

  getuser(userId: number) {
    this.myRequestModel.usersId = userId
    //Routing Parameters
    this.router.navigate(['/user-specific-Display', this.myRequestModel.usersId]);
  }




}
