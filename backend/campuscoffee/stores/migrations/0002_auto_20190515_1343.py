# Generated by Django 2.1.8 on 2019-05-15 04:43

from django.db import migrations, models


class Migration(migrations.Migration):

    dependencies = [
        ('stores', '0001_initial'),
    ]

    operations = [
        migrations.RemoveField(
            model_name='menu',
            name='comment',
        ),
        migrations.AddField(
            model_name='menu',
            name='price',
            field=models.CharField(max_length=50, null=True),
        ),
    ]