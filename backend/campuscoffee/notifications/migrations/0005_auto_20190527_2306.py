# Generated by Django 2.1.8 on 2019-05-27 14:06

from django.db import migrations, models
import django.db.models.deletion


class Migration(migrations.Migration):

    dependencies = [
        ('notifications', '0004_notification_timer'),
    ]

    operations = [
        migrations.AlterField(
            model_name='notification',
            name='timer',
            field=models.ForeignKey(null=True, on_delete=django.db.models.deletion.PROTECT, related_name='time', to='stores.Order'),
        ),
    ]