import { Component, OnInit } from '@angular/core';
import { NotificationService } from '../../services/notification.service';
import { NgFor } from '@angular/common';
import {FormsModule} from '@angular/forms';

@Component({
  selector: 'app-notifications',
  imports: [FormsModule, NgFor],
  templateUrl: './notifications.component.html',
  styleUrl: './notifications.component.css'
})
export class NotificationsComponent implements OnInit{
  notifications: string[] = [];
  newMessage: string = '';

  constructor(private notificationService: NotificationService) {}

  ngOnInit() {
    this.loadNotifications();
  }

  async loadNotifications() {
    try {
      const response = await this.notificationService.getNotifications();
      this.notifications = response.data.map((item: any) => item.message);
    } catch (error) {
      console.error('Error fetching notifications:', error);
    }
  }

  async sendNotification() {  
    if (this.newMessage.trim() === '') {
      return;
    }
  
    try {
      await this.notificationService.sendNotification(this.newMessage);
  
      // Instead of refreshing, update the UI immediately
      this.notifications.unshift(this.newMessage); // Add the new message to the top of the list
  
      this.newMessage = ''; // Clear input field
    } catch (error) {
      console.error("❌ Error sending notification:", error);
    }
  }
  

}
