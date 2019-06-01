from rest_framework import serializers
from . import models

class MenuSerializer(serializers.ModelSerializer):

    # Meta class is extra information
    class Meta:
        model = models.Menu
        fields = '__all__'


class OrderMenuSerializer(serializers.ModelSerializer):

    # Order class is extra information
    class Meta:
        model = models.Order
        fields = '__all__'


class OrderSerializer(serializers.ModelSerializer):

    menu = MenuSerializer()
    # Order class is extra information
    class Meta:
        model = models.Order
        fields = (
            'id',
            'menu',
            'buyer',
            'count',
            'option',
            'progress',
            'store',
            'price'
        )
