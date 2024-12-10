:running:BGAFlowLayout-Android:running:
============

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/cn.bingoogolapple/bga-flowlayout/badge.svg)](https://maven-badges.herokuapp.com/maven-central/cn.bingoogolapple/bga-flowlayout)

Android流式布局，可配置是否将每一行的空白区域平均分配给子控件。

最开始是参考[鸿洋_的这篇文章](http://blog.csdn.net/lmj623565791/article/details/38352503)的思路实现的，后来根据产品经理出的需求，增加了将每一行的空白区域平均分配给子控件。

demo中分别演示了在xml使用方式和在java代码中动态添加

### 效果图

![Image of 平均分配剩余空间](http://7xk9dj.com1.z0.glb.clouddn.com/flowlayout/screenshots/bga-flowlayout-demo1.gif)
![Image of 不平均分配剩余空间](http://7xk9dj.com1.z0.glb.clouddn.com/flowlayout/screenshots/bga-flowlayout-demo2.gif)

### Gradle依赖

```groovy
dependencies {
    compile 'cn.bingoogolapple:bga-flowlayout:latestVersion@aar'
}
```

### 自定义属性说明

```xml
<declare-styleable name="BGAFlowLayout">
    <!-- 标签之间的水平间距 -->
    <attr name="fl_horizontalChildGap" format="dimension" />
    <!-- 标签之间的垂直间距 -->
    <attr name="fl_verticalChildGap" format="dimension" />
    <!-- 是否平均分配每一行的剩余水平方向的空白区域给该行的标签 -->
    <attr name="fl_isDistributionWhiteSpacing" format="boolean" />
</declare-styleable>
```

## 作者联系方式

| 个人主页 | 邮箱 |
| ------------- | ------------ |
| <a  href="https://www.bingoogolapple.cn" target="_blank">bingoogolapple.cn</a>  | <a href="mailto:bingoogolapple@gmail.com" target="_blank">bingoogolapple@gmail.com</a> |

| 个人微信号 | 微信群 | 公众号 |
| ------------ | ------------ | ------------ |
| <img width="180" alt="个人微信号" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/BGAQrCode.png"> | <img width="180" alt="微信群" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/WeChatGroup1QrCode.jpg"> | <img width="180" alt="公众号" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/GongZhongHao.png"> |

| 个人 QQ 号 | QQ 群 |
| ------------ | ------------ |
| <img width="180" alt="个人 QQ 号" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/BGAQQQrCode.jpg"> | <img width="180" alt="QQ 群" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/QQGroup1QrCode.jpg"> |

## 打赏支持作者

如果您觉得 BGA 系列开源库或工具软件帮您节省了大量的开发时间，可以扫描下方的二维码打赏支持。您的支持将鼓励我继续创作，打赏后还可以加我微信免费开通一年 [上帝小助手浏览器扩展/插件开发平台](https://github.com/bingoogolapple/bga-god-assistant-config) 的会员服务

| 微信 | QQ | 支付宝 |
| ------------- | ------------- | ------------- |
| <img width="180" alt="微信" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/donate-wechat.jpg"> | <img width="180" alt="QQ" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/donate-qq.jpg"> | <img width="180" alt="支付宝" src="https://github.com/bingoogolapple/bga-god-assistant-config/raw/main/images/donate-alipay.jpg"> |

## 作者项目推荐

* 欢迎您使用我开发的第一个独立开发软件产品 [上帝小助手浏览器扩展/插件开发平台](https://github.com/bingoogolapple/bga-god-assistant-config)
