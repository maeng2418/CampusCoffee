# Generated by Django 2.1.8 on 2019-05-28 07:04

import django.core.validators
from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('stores', '0021_auto_20190528_1557'),
    ]

    operations = [
        migrations.AlterField(
            model_name='order',
            name='progress',
            field=models.IntegerField(null=True, validators=[django.core.validators.MinValueValidator(1), django.core.validators.MaxValueValidator(2)]),
        ),
    ]
