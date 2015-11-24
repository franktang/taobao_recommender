
# coding: utf-8

# In[4]:

import graphlab
import os

if os.path.exists('user_history.csv'):
    data = graphlab.SFrame('user_history.csv')
else:
    print "file not found"
    
data.head()


# In[5]:

model = graphlab.item_similarity_recommender.create(data,
                                                    user_id="user_id",
                                                    item_id="item_id")


# In[6]:

sim_results = model.get_similar_items(k=200)
print(sim_results)


# In[ ]:

sim_results.save('similar_itmes_traing_result.csv', format='csv')


# In[ ]:



