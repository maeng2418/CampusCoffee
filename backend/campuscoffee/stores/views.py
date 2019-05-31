from django.shortcuts import render
from rest_framework.views import APIView
from rest_framework.response import Response
from rest_framework import status
from . import models, serializers
from campuscoffee.users import models as user_models
from campuscoffee.stores import models as store_models
from campuscoffee.notifications import views as notifications_view

# Create your views here.
class ListAllMenus(APIView):

    def get(self, request, format=None):
        all_menus =models.Menu.objects.all()

        serializer = serializers.MenuSerializer(all_menus, many=True)

        return Response(data=serializer.data)


class OrdersMenu(APIView):

    def post(self, request, store_id, format=None):

        serializer = serializers.OrderMenuSerializer(data=request.data, partial=True)

        try:
            found_store = user_models.User.objects.get(id=store_id)
        except user_models.User.DoesNotExist:
            return Response(status=status.HTTP_404_NOT_FOUND)

        if serializer.is_valid():

            serializer.save(store=found_store)

            notifications_view.create_notification(found_store, serializer.data['buyer'], serializer.data['menu'], serializer.data['count'], serializer.data['option'], serializer.data['price'])

            return Response(data=serializer.data, status=status.HTTP_201_CREATED)

        else:

            return Response(data=serializer.errors, status=status.HTTP_400_BAD_REQUEST)

class OrdersList (APIView):
    
    def get(self, request, format=None):

        user = request.user

        order_list = models.Order.objects.filter(store=user)

        serializer = serializers.OrderSerializer(order_list, many=True)

        return Response(data=serializer.data, status=status.HTTP_200_OK)

        '''
        try:
            order_list = models.Order.objects.filter(store=store_id)

        except models.Order.DoesNotExist:
             return Response(status=status.HTTP_404_NOT_FOUND)

        serializer = serializers.OrderSerializer(order_list, many=True)

        return Response(data=serializer.data, status=status.HTTP_200_OK)
        '''

class Order (APIView):

    def get(self, request, store_id, order_id, format=None):

        try:
            order = models.Order.objects.get(id=order_id, store=store_id)

        except models.Order.DoesNotExist:
             return Response(status=status.HTTP_404_NOT_FOUND)

        serializer = serializers.OrderSerializer(order)

        return Response(data=serializer.data, status=status.HTTP_200_OK)

    def delete(self, request, store_id, order_id, format=None):

        try:
            order = models.Order.objects.get(id=order_id, store=store_id)
            order.delete()
            return Response(status=status.HTTP_204_NO_CONTENT)

        except models.Order.DoesNotExist:
            return Response(status=status.HTTP_404_NOT_FOUND)

class SetProgress (APIView):

    def find_own_order(self, order_id, user):
            try:
                order = models.Order.objects.get(id=order_id, store=user) # Only creator can see the image
                return order
            except models.Order.DoesNotExist:
                return None
    
    def put(self, request, order_id, foramt=None):

        user = request.user

        order = self.find_own_order(order_id, user)

        if order is None:

            return Response(status=status.HTTP_401_UNAUTHORIZED)

        serializer = serializers.OrderSerializer(order, data=request.data, partial=True)

        if serializer.is_valid():

            serializer.save(store=user)

            return Response(data=serializer.data, status=status.HTTP_204_NO_CONTENT)

        else:

            return Response(data=serializer.errors, status=status.HTTP_400_BAD_REQUEST)

    def get(self, request, order_id, format=None):

        user = request.user

        order = self.find_own_order(order_id, user)

        if order is None:

            return Response(status=status.HTTP_401_UNAUTHORIZED)

        serializer = serializers.OrderSerializer(order, partial=True)

        return Response(data=serializer.data, status=status.HTTP_200_OK)

class buyerList(APIView):

    def get(self, request, buyer, format=None):

        try:
            order = models.Order.objects.filter(buyer=buyer)

        except models.Order.DoesNotExist:
             return Response(status=status.HTTP_404_NOT_FOUND)

        serializer = serializers.OrderSerializer(order, many=True)

        return Response(data=serializer.data, status=status.HTTP_200_OK)