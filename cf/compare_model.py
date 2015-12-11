
# coding: utf-8

# In[2]:

import graphlab
import os

if os.path.exists('user_history.csv'):
    data = graphlab.SFrame('user_history.csv')
else:

    print "file not found"

data.head()


# In[3]:

train, test = graphlab.recommender.util.random_split_by_user(data)


# In[4]:

m1 = graphlab.item_similarity_recommender.create(train)


# In[5]:

m2 = graphlab.ranking_factorization_recommender.create(train)


# In[6]:

graphlab.recommender.util.compare_models(test, [m1, m2], model_names=["m1", "m2"], metric='precision_recall')


# In[ ]:



