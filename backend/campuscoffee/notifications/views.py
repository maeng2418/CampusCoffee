from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from . import models, serializers
from campuscoffee.stores import models as store_models

# Create your views here.
class Notifications(APIView):

    def get(self, request, format=None):

        user = request.user

        notifications = models.Notification.objects.filter(store=user)

        serializer = serializers.NotificiaitionSerializer(notifications, many=True)

        return Response(data=serializer.data, status=status.HTTP_200_OK)


class Timer(APIView):

    def find_own_notification(self, notification_id, user):
            try:
                notification = models.Notification.objects.get(id=notification_id, store=user) # Only creator can see the image
                return notification
            except models.Notification.DoesNotExist:
                return None

    def put(self, request, notification_id, format=None):

        user = request.user

        notification = self.find_own_notification(notification_id, user)

        if notification is None:

            return Response(status=status.HTTP_401_UNAUTHORIZED)

        serializer = serializers.NotificiaitionSerializer(notification, data=request.data, partial=True)

        if serializer.is_valid():

            serializer.save(store=user)

            return Response(data=serializer.data, status=status.HTTP_204_NO_CONTENT)

        else:

            return Response(data=serializer.errors, status=status.HTTP_400_BAD_REQUEST)

def create_notification(store, buyer = None, menu = None, count = None, option = None, price = None):
    
    notification = models.Notification.objects.create(
        store=store,
        buyer=buyer,
        menu = store_models.Menu.objects.get(id=menu),
        count = count,
        option = option,
        price=price,
    )

    notification.save()