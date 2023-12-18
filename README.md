本电子商务网站为华南理工大学计算机科学与工程学院《网络应用开发》课程的课程设计，根据课程设计要求，现将代码开源。

学号：202130441245

姓名:刘亚南

网站网址：http://8.138.97.40:8080/CakeShop
普通用户账户：qwe  密码：qwe
管理员账户：admin  密码：admin

下面对本项目中所用到的源代码的功能进行简单的说明：

##### src

###### dao

- TypeDao 商品类型相关的Dao
- OrderDao 订单相关的Dao
- GoodsDao 商品相关的Dao
- UserDao 用户相关的Dao

###### model

- page  页面类
- order 订单类
- Goods 商品类
- User 用户类
- type 类型类

###### filter

- EncodeFilter 过滤器，主要用于解决乱码问题。

###### listener
- ApplicationListener 监听器，用于监听用户的行为。

###### service

- GoodsService 与商品相关的服务，提供“查找商品”、“添加商品”、“删除商品”、“更新商品”、“展示所有商品”的功能。
- TypeService 与商品类型相关的服务。
- OrderService 与订单相关的服务，提供“获取所有订单”、“添加订单”、“删除订单”、“结算”功能。
- UserService 与用户相关的服务，提供“注册”和“登录”的功能。

###### servlet

- OrderSubmmit 添加购物车信息
- GoodsBuy 向购物车添加商品
- AdminOrderDelete 删除订单
- AdminGoodsdelete 删除商品
- GoodsSearch 搜索商品
- UserLogin 用户登录
- UserLogout 用户登出
- AdminLogin 管理员登录
- AdminLogout 管理员登出
- OrderConfirm 结算
- UserRegister 注册
- ShowAllProduct 展示所有商品
- GoodsDetails 展示商品详情页
- AdminOrderStatus 更新订单状态
- AdminGoodsEdit 更新商品信息
- AdminGoodsAdd 管理员增加商品

###### utils

- DateSourceUtils 提供与数据库连接相关方法的工具类
- PriceUtils 提供商品计算价格的工具类

##### web

###### client

- goods_add 添加商品页面
- goods_delete 商品删除页
- goods_list 所有商品页面
- goods_details 商品详情页
- goods_cart 购物车页面
- DeleteCart 删除订单页面
- DeleteProduct 删除商品页面
- index 索引页面
- login 登录页面
- user_center 用户中心页面
- goods_recommend 商品推荐页
- register 注册页面
- goods_search 搜索结果页面
- order_list  所有订单页面
- order_submmit 订单提交页面
