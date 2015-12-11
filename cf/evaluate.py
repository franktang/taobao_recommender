
# coding: utf-8

# In[2]:

import graphlab
import os

if os.path.exists('user_history.csv'):
    data = graphlab.SFrame('user_history.csv')
else:
    print "file not found"
    
data.head()


# In[4]:

train, test = graphlab.recommender.util.random_split_by_user(data,
                                                    user_id="user_id",
                                                    item_id="item_id")


# In[6]:

model = graphlab.item_similarity_recommender.create(train,
                                                    user_id="user_id",
                                                    item_id="item_id")


# In[7]:

eval = model.evaluate(test)


# In[8]:

print(eval)


# In[9]:

eval.print_rows(num_rows=18, num_columns=3)


# In[10]:

eval.save('evaluate_result.csv', format='csv')


# In[11]:

model.show()


# In[12]:

model.summary()


# In[ ]:



