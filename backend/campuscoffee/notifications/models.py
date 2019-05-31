from django.db import models

# Create your models here.
from django.db import models
from campuscoffee.users import models as user_models
from campuscoffee.stores import models as store_models


class Notification(store_models.TimeStampedModel):

    store = models.ForeignKey(user_models.User, on_delete=models.PROTECT, related_name='store')
    buyer = models.CharField(null=True, max_length=50)
    menu = models.ForeignKey(store_models.Menu, on_delete=models.PROTECT, related_name='menu', null=True)
    count = models.CharField(null=True, max_length=50)
    option = models.TextField(null=True)
    price = models.CharField(null=True, max_length=50)

    class Meta:
        ordering = ['-created_at']

    def __str__(self):
        return 'Store: {} - Buyer: {}'.format(self.store, self.buyer)