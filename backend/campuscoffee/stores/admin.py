from django.contrib import admin
from . import models

# Register your models here.
@admin.register(models.Menu)
class MenuAdmin(admin.ModelAdmin):

    list_display_links = (
        'creator',
    )

    list_display = (
        'creator',
        'file',
        'name',
        'temperature',
        'price',
    )

@admin.register(models.Order)
class OrderAdmin(admin.ModelAdmin):
    
    list_display = (
        'store',
        'menu',
        'count',
        'option',
        'price',
        'progress',
    )