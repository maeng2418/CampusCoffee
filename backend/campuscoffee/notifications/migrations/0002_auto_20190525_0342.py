# Generated by Django 2.1.8 on 2019-05-24 18:42

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('notifications', '0001_initial'),
    ]

    operations = [
        migrations.AddField(
            model_name='notification',
            name='buyer',
            field=models.CharField(max_length=50, null=True),
        ),
        migrations.AddField(
            model_name='notification',
            name='count',
            field=models.CharField(max_length=50, null=True),
        ),
        migrations.AddField(
            model_name='notification',
            name='price',
            field=models.CharField(max_length=50, null=True),
        ),
    ]
