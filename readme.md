##Taobao recommender

###Folder
* ``\cf``: for collaborative filtering
* ``\lsh``: for locality-sensitive hashing
* ``\termsSimilarity``: for calculating terms similarity after lsh
* ``\result``: final result + result intergration source code

###Java-LSH lib
In order to execute the mapper reducer in ``\lsh``, a java lib may be needed, which you can find in this [release page of java-LSH](https://github.com/tdebatty/java-LSH/releases).

###Dataset
Since dataset is too big size, therefore, the dataset is put in [oneDrive](http://1drv.ms/1lXLOGF)

> Note:  
> 
> 1. dim\_fashion\_matchsets\_new.txt: existing collocation
> 2. dim\_items(new).txt: entire item list
> 3. test\_items\_new.txt: test data
> 4. user\_bought\_history.txt: user purchase history lost

###Result
There are some intermediate result (e.g. the result of lsh, terms similarity) which is not included inside due to size limitation, you may click the [oneDrive link](http://1drv.ms/1QdrVsD) for the intermediate result