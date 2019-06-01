from django.urls import path
from . import views

app_name = "menus"
urlpatterns = [
    path("all/", view=views.ListAllMenus.as_view(), name="all_menus"),
    path("<int:store_id>/order/", view=views.OrdersMenu.as_view(), name ="order_menu"),
    path("orders/", view=views.OrdersList.as_view(), name ="order_List"),
    path("all/orders/<buyer>/", view=views.buyerList.as_view(), name ="buyer_List"),
    path("<int:store_id>/orders/<int:order_id>/", view=views.Order.as_view(), name ="order"),
    path("orders/<int:order_id>/progress", view=views.SetProgress.as_view(), name ="orderProgress"),
]