import { Injectable } from '@angular/core';
import { HttpClient, HttpEvent, HttpRequest } from '@angular/common/http';
import { Observable } from 'rxjs';
import { UserModel } from './users.model';
import { RequestModel } from './request.model';


@Injectable({ providedIn: 'root' })
export class ReimbursmnetService {

  addRequestUrl = "http://localhost:8080/api/request";
  allpendingUrl = "http://localhost:8080/api/request/pending";
  allresolveUrl = "http://localhost:8080/api/request/resolve";
  approveUrl = "http://localhost:8080/api/request";
  userResolve = "http://localhost:8080/api/request/resolve";
  spacificUrl = "http://localhost:8080/api/request/spacific";
  userPending = "http://localhost:8080/api/request/pend";
  allEmployee = "http://localhost:8080/api/users/employee";
  userLogin = "http://localhost:8080/api/users/login";
  userProfile = "http://localhost:8080/api/users/profile";
  rejectUrl = "http://localhost:8080/api/request/reject";
  userUpdate = "http://localhost:8080/api/users/update";
  registerUrl = "http://localhost:8080/api/users/register";
  sendEmailUrl = "http://localhost:8080/api/users/email";
  ImageUrl = "http://localhost:8080/files/";
  ShowImage = "http://localhost:8080/";


  constructor(private http: HttpClient) { }

  // use Observable when you add or update
  addRequestService(newRequest: RequestModel): Observable<RequestModel> {
    return this.http.post<RequestModel>(this.addRequestUrl, newRequest);
  }

  UserPendingRequestService(userId: number): Observable<RequestModel[]> {
    return this.http.get<RequestModel[]>(this.userPending + "/" + userId);

  }
  UserResolveRequestService(userId: number): Observable<RequestModel[]> {
    return this.http.get<RequestModel[]>(this.userResolve + "/" + userId);
  }
  EmployeeProfileService(userId: number): Observable<UserModel> {
    return this.http.get<UserModel>(this.userProfile + "/" + userId);

  }
  updateUserInfoService(updateUser: UserModel): Observable<UserModel> {
    return this.http.put<UserModel>(this.userUpdate + "/" + updateUser.id, updateUser);
  }
  allPendingRequstsService() {
    return this.http.get<RequestModel[]>(this.allpendingUrl);
  }
  AllResolvedRequstService() {
    return this.http.get<RequestModel[]>(this.allresolveUrl);
  }
  SpacificEmployeeService(reqUserId: number) {
    return this.http.get<RequestModel[]>(this.spacificUrl + "/" + reqUserId);
  }
  approveRequestService(reqId: number) {
    return this.http.patch<RequestModel>(this.approveUrl + "/" + reqId, RequestModel);
  }
  rejectRequestService(reqId: number) {
    return this.http.patch<RequestModel>(this.rejectUrl + "/" + reqId, RequestModel);
  }
  sendEmailService(userId: number, flag: number) {
    return this.http.patch<UserModel>(this.sendEmailUrl + "/" + userId + "/" + flag, UserModel);
  }
  validataLoginService(logUser: UserModel): Observable<UserModel> {
    return this.http.post<UserModel>(this.userLogin, logUser);
  }
  registrationService(addUser: UserModel): Observable<UserModel> {
    return this.http.post<UserModel>(this.registerUrl, addUser);
  }
  allEmployeeService() {
    return this.http.get<UserModel[]>(this.allEmployee);
  }
  upload(file: File, id: number): Observable<HttpEvent<File>> {
    const formData: FormData = new FormData();
    formData.append('file', file);
    const req = new HttpRequest('POST', `${this.ImageUrl}` + id, formData, {
      reportProgress: true,
      responseType: 'json'
    });
    return this.http.request(req);
    // return this.http.post<HttpEvent<File>>(this.ImageUrl + id, formData);
  }

  getFiles(): Observable<any> {
    return this.http.get(`${this.ShowImage}files`, { responseType: "blob" });
  }

  getFilesByRb(id: number): Observable<any> {
    return this.http.get(`${this.ShowImage}files/rbid/` + id, { responseType: "blob" });
  }
}


