## better-together
一个写着玩的小项目，源于生活中的两个人一起完成一些既定目标，互相监督，作为一些数据记录

## 目标功能
- [x] 邮箱验证码注册
- [ ] 手机固然很好，但是手机要运行商接口，需要Money。
- [x] 用户搜索-申请为伙伴-同意/拒绝成为伙伴
- [x] 发动态（仅伙伴可见、仅自己可见）。
    - 发送的时候采用推的模式将动态记录推送到好友的动态收件箱中。
    - 不支持无限制的获取成为好友之前的动态数据，目前只会在成为好友时，采用拉的模式，主动拉取各自最新的一定数量的动态发送到动态收件箱中。
    - 好友关系解散时，清空好友动态中对方发的动态数据。
- [ ] 消息发送全部密文。
    - 成为好友时在好友关系中生成双方交流秘钥，采用对称加密，不支持二次变更。聊天记录每次返回都是密文，用户需要调用一键翻译功能，手动输入秘钥才可解密。
- [ ] 任务模块预想：
    - 任务可分为自己的任务， 分配监督人。也可任务共享， 增加任务执行人，则任务双方都需要完成。
    - 任务发起方在指定任务监督人或执行人之后，需要双方最终都对任务进行确认，任务才生效。
    - 任务分为一次性任务和周期性任务即每日任务、每周任务、每月任务、 每年任务
    - 一次性任务需要指定开始和截止时间，周期性任务则由系统自动计算时间；任务截止后，会生成任务结算清单，记录任务要求和实际完成情况。
    - 如何确定任务的完成情况？？任务大多在系统上没有一个明确的指标让系统能够自动识别完成结果，所以还是需要用户自己手动确认完成情况。
    - 任务奖励的分类设想
        - 文字性的任务奖励，则线下双方解决，系统只负责记录，以及由奖励方负责手工确认奖励的完成情况。
        - 积分任务奖励，任务完成后获取对应任务奖励积分，至于积分的用途，后续应考虑积分兑换功能，且可兑换的奖励也是由任务发起人定义的。
- [ ] 获取的奖励特权列表记录，每次获取到的需要记录。有使用和未使用状态记录。
- [ ] 任务可设置定时提醒
- [ ] 发心愿池， 与任务无关。 每天随机将心愿池的数据不经意间让伙伴看到，伙伴可在现实中去表现。用以一些心思，不需要明言对方。看对方能否注意到。
- [ ] 摒弃好友唯一绑定限制，变为可选。即专属伙伴和普通伙伴。至于二者有什么区别以及他们的互动，需要考虑一下。将原来好友关系解除时的双方确认机制转移到专属伙伴上，普通伙伴可单方面解除关系。
- [ ] 增加家族概念， 可以由某个用户创建家族，邀请好友一起进入家族。前面所有的任务都可以放到家族中，这样任务就可以家族共享，以家族为单位进行一些活动。


