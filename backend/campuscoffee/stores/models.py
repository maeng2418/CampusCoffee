from django.db import models
from campuscoffee.users import models as user_models
from django.core.validators import MaxValueValidator, MinValueValidator 

# Create your models here.
class TimeStampedModel(models.Model):

    created_at = models.DateTimeField(auto_now_add=True) 
    updated_at = models.DateTimeField(auto_now=True)
    
    class Meta:
        abstract = True

class Menu(TimeStampedModel):

    """ Menu Model """
    TEMPERATURE_CHOICES = (
        ("hot", "Hot"),
        ("ice", "Ice")
    )
    file = models.ImageField()
    name = models.TextField()
    creator = models.ForeignKey(user_models.User, null=True, on_delete=models.CASCADE)
    temperature = models.CharField(null=True, max_length=80, choices = TEMPERATURE_CHOICES)
    price = models.CharField(null=True, max_length=50)

    @property
    def order_count(self):
        return self.orders.all().count()
        
    def __str__(self):
        return '{} - {}'.format(self.creator, self.name)


class Order(TimeStampedModel):

    """ Order Model """


    store = models.ForeignKey(user_models.User, null=True, on_delete=models.CASCADE, related_name='stores')
    buyer = models.CharField(null=True, max_length=50)
    menu = models.ForeignKey(Menu, null=True, on_delete=models.CASCADE, related_name='menus')
    count = models.IntegerField(null=True, validators=[MinValueValidator(1), MaxValueValidator(10)])
    option = models.TextField(null=True)
    price = models.IntegerField(null=True, validators=[MinValueValidator(1500)])
    progress = models.IntegerField(null=True, validators=[MinValueValidator(1), MaxValueValidator(2)])
    
    class Meta:
        ordering = ['-created_at']