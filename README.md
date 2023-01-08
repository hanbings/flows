![Fluocean](https://picture.hanbings.com/2023/01/06/db6ea1fb54490.png)

<h1 align="center">🌊 Fluocean</h1>
<h5 align="center">海纳百川 有容乃大！</h5>

## 🍀 这是什么？

**洋流**是一条 OAuth 客户端的辅助工具，通过低耦合的设计给用户高自定义度的接口，并且洋流集成了常见 OAuth 平台的封装，提供了光速集成第三方登录的能力。

**洋流**的主要特性如下：

- 常见的 OAuth 平台适配
- 支持自定义 State 生成器、自定义 Http 客户端、自定义 Json 解析器
- 提供通用默认实现以便支持未适配 OAuth 平台
- 默认 Http 客户端实现支持 Socks 代理
- 可爱



**Github OAuth 示例**

洋流提供了许多的重载方法，用于应对不同情况下的请求，有些带自有请求头的，也有要求必须要 Scope 的。

```java
// 创建 OAuth 原始处理器
OAuth<GithubAccess, GithubAccess.Wrong> oauth = new GithubOAuth(
	"id",
	"secret",
	"https://exmaple.com/api/v0/login/oauth/github/callback"
);

// 生成授权 url
String url = oauth.authorize();
// 生成带参数或指定 scope
String spec = oauth.authorize(List.of("email"), Map.of("Accept", "application/json"));
        
//解析回调的 url 并获取 token
// 输入原始 url 自动解析 code 以及 state
oauth.token("url");
// 更改回调地址
oauth.token("url", "redirect");
// 手动指定参数
oauth.token("code", "state", "redirect");
        
// 处理返回值
oauth.token("code", "state", "redirect")
	.succeed(data -> System.out.println(data.accessToken()))
	.fail(wrong -> System.out.println(wrong.errorDescription()))
	.except(throwable -> System.out.println(throwable.getMessage()));
        
// 假设请求成功 直接获取数据
GithubAccess access = oauth.token("code", "state", "redirect").data();
```

**使用 Socks 代理**

```java
oauth.proxy(() ->
	new Request.Proxy(
		Proxy.Type.SOCKS,
		"127.0.0.1",
		10086,
		"username",
		"password"
	)
);
```

**更换 State 生成器**

默认随机生成 UUID 并设置 300 秒有效期

```java
oauth.state(
    Lazy.of(() -> new OAuthState(300, () -> UUID.randomUUID().toString()))
);
```

**更换 Http 客户端**

默认使用 Okhttp 发起请求

```java
// 实现比较繁杂 就不展示啦 x
oauth.request(Lazy.of(OAuthRequest::new));
```

**更换 Json 解析器**

默认使用 Gson 作为 Json 解析器

```java
oauth.serialization(
	Lazy.of(() -> new Serialization() {
		final Gson gson = new Gson();

		@Override
		public <T> T object(Class<T> type, String raw) {
			return gson.fromJson(raw, type);
		}

		@Override
		public <K, V> Map<K, V> map(Class<K> key, Class<V> value, String raw) {
			return gson.fromJson(raw, new TypeToken<Map<K, V>>() {
			}.getType());
		}

		@Override
		public <T> List<T> list(Class<T> type, String raw) {
			return gson.fromJson(raw, new TypeToken<List<T>>() {
			}.getType());
		}
	})
);
```

## ⚡️ 快速开始

*稍后补充*

## 🍉 一些小事情

**F：这是一个什么样的项目？**

Q：这是一个计划用于生产环境，长期维护和对社区友善且开放的个人项目。

**F：为什么使用 JDK 17 而不是 JDK 11 甚至是 JDK 8？**

Q：JDK 17 是一个 LTS （long-term support）版本，它拥有长达十年的支持时间。JDK 17 中拥有的特性例如 Record 和 Switch 模式匹配，它们都可以有效提高开发者效率。许多框架已经开始迁移到新版本的 JDK17，例如 [Spring Framework 6](https://spring.io/blog/2021/09/02/a-java-17-and-jakarta-ee-9-baseline-for-spring-framework-6) 和 [Spring Boot 3](https://spring.io/blog/2022/05/24/preparing-for-spring-boot-3-0)，自这些框架新版本发布后的新项目将会基于 JDK17，开发者已经没有什么理由不使用新版本了。另外，基于旧版本 JDK 的项目在完成初期开发后换用组件的几率很小，对于旧版本 JDK 的支持所带来的收益是小于成本的。

**F：商业计划？**

Q：本项目基于 Apache 2.0 许可证，项目本身完全是免费开源的，同样的，项目依赖库也将选择可商用开源许可的开源项目，使用本项目开发的独立项目可用于商业用途，请阅读 `开源许可` 部分。


## ⚖ 开源许可

本项目使用 [Apache License, Version 2.0](https://www.apache.org/licenses/LICENSE-2.0.html) 许可协议进行开源。

本项目是**非盈利性**项目。

## 🍀 关于开源

开源是一种精神。

开源运动所坚持的原则：

1. 坚持开放与共享，鼓励最大化的参与与协作。
2. 尊重作者权益，保证软件程序完整的同时，鼓励修改的自由以及衍生创新。
3. 保持独立性和中立性。

与来自五湖四海的开发者共同**讨论**技术问题，**解决**技术难题，**促进**应用的发展是开源的本质目的。

**众人拾柴火焰高，开源需要依靠大家的努力，请自觉遵守开源协议，弘扬开源精神，共建开源社区！**