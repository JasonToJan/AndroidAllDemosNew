

##### update on 2019.12.19
      作用：
      整体App的第一层入口。
      直接进入main依赖库，跳转到主页。
      没有任何UI，没有什么逻辑。
      但是这个类似于一个 根部。

      app -> main 进入主页        依赖commonlib
          -> other 进入其它组件   依赖commonlib

            主要是为了把main也当做一个普通的组件，因为他们都处于同一等级。