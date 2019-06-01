from django.apps import AppConfig


class UsersAppConfig(AppConfig):

    name = "campuscoffee.users"
    verbose_name = "Users"

    def ready(self):
        try:
            import campuscoffee.users.signals  # noqa F401
        except ImportError:
            pass
