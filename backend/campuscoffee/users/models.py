from django.contrib.auth.models import AbstractUser
from django.db import models
from django.urls import reverse
from django.utils.translation import ugettext_lazy as _


class User(AbstractUser):

    # First Name and Last Name do not cover name patterns
    # around the globe.
    GENDER_CHOICES = (
        ("male", "Male"),
        ("female", "Female"),
        ("not-specified", "Not specified")
    )
    name = models.CharField(_("Name of User"), blank=True, max_length=255)
    phone = models.CharField(null=True, max_length=140)
    gender = models.CharField(null=True, max_length=80, choices = GENDER_CHOICES)

    def get_absolute_url(self):
        return reverse("users:detail", kwargs={"username": self.username})
