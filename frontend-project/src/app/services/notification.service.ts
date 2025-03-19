import { Injectable } from '@angular/core';
import axios from 'axios';

@Injectable({
  providedIn: 'root'
})
export class NotificationService {
  private apiUrl = 'http://localhost:8080/api/notifications';
  
  async sendNotification(message: string) {
    return axios.post(
      this.apiUrl + "/send",
      { message },  // Ensure we send JSON object
      {
        headers: {
          'Content-Type': 'application/json'  // Important: Set Content-Type
        }
      }
    );
  }

  async getNotifications() {
    return axios.get(this.apiUrl);
  }
}
