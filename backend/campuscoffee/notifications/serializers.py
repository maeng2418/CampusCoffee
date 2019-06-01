from rest_framework import serializers
from . import models
from campuscoffee.users import serializers as user_serializers
from campuscoffee.stores import serializers as store_serializers

class NotificiaitionSerializer(serializers.ModelSerializer):

    #store = user_serializers.ListUserSerializer()
    menu = store_serializers.MenuSerializer()
    
    class Meta:
        model = models.Notification
        fields = (
            'id',
            'menu',
            'created_at',
            'buyer',
            'option',
            'price',
        )