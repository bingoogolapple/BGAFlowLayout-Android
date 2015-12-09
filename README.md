:running:BGAFlowLayout-Android:running:
============

[![Maven Central](https://maven-badges.herokuapp.com/maven-central/cn.bingoogolapple/bga-flowlayout/badge.svg)](https://maven-badges.herokuapp.com/maven-central/cn.bingoogolapple/bga-flowlayout)

Android流式布局，可配置是否将每一行的空白区域平均分配给子控件。

最开始是参考[鸿洋_的这篇文章](http://blog.csdn.net/lmj623565791/article/details/38352503)的思路实现的，后来根据产品经理出的需求，增加了将每一行的空白区域平均分配给子控件。

demo中分别演示了在xml使用方式和在java代码中动态添加

### 效果图
![Image of 平均分配剩余空间](http://7xk9dj.com1.z0.glb.clouddn.com/flowlayout/screenshots/bga-flowlayout-demo1.gif)
![Image of 不平均分配剩余空间](https://raw.githubusercontent.com/bingoogolapple/BGAFlowLayout-Android/server/screenshots/flowlayout2.gif)

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

### 关于我

| 新浪微博 | 个人主页 | 邮箱 | BGA系列开源库QQ群 |
| ------------ | ------------- | ------------ | ------------ |
| <a href="http://weibo.com/bingoogol" target="_blank">bingoogolapple</a> | <a  href="http://www.bingoogolapple.cn" target="_blank">bingoogolapple.cn</a>  | <a href="mailto:bingoogolapple@gmail.com" target="_blank">bingoogolapple@gmail.com</a> | ![BGA_CODE_CLUB](http://7xk9dj.com1.z0.glb.clouddn.com/BGA_CODE_CLUB.png?imageView2/2/w/200) |

