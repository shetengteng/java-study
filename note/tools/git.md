# [git 在提交之前撤销add操作](https://www.cnblogs.com/wuchanming/p/5428897.html)



# 问题

在使用git时，在未添加.ignore文件前使用 git add . 将所有文件添加到库中，不小心将一些不需要加入版本库的文件加到了版本库中。由于此时还没有提交所以不存在HEAD版本，不能使用 git reset HEAD命令。

# 解决

使用

git rm -r --cached .

 

不小心使用git pull合并冲突，如果想把当前的修改都放弃，你可以用下面的命令回到合并之前的状态：
git reset --hard HEAD
或者如果你已经把合并的代码提交了，这时候的撤销需要下面的命令：
git reset --hard ORIG_HEAD
但是刚才这条命令在某些情况会很危险,如果你把一个已经被另一个分支合并的分支给删了,那么 以后在合并相关的分支时会出错。