# Generated by Django 2.1.8 on 2019-05-27 08:26

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('stores', '0007_auto_20190520_1535'),
    ]

    operations = [
        migrations.AddField(
            model_name='order',
            name='timer',
            field=models.CharField(max_length=50, null=True),
        ),
    ]