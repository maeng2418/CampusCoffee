from django.urls import path
from . import views

app_name = "notifications"
urlpatterns = [
    path("", view=views.Notifications.as_view(), name="notifications"),
    path("<int:notification_id>/timer/", view=views.Timer.as_view(), name="timer"),
    
]