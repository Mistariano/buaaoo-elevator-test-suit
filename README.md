# BUAA OO 电梯单元测试IO接口

为电梯单元自动化黑箱测试搭建的IO接口

与官方提供的程序接口格式一致，可以直接替换使用

## Getting Started

将编译好的JAR包替换课程组提供的两个包

输入格式变为带有时间的输入：

```
[0.0]1-FROM-1-TO-2
[1.0]2-FROM-1-TO-2
[2.0]3-FROM-1-TO-2
```
全部输入完毕后手动^D给出EOF，若格式无误会自动按给定的时间顺序输入电梯

## Contributing

欢迎各种形式的良性Issue与PR

## License

GPL 2.0