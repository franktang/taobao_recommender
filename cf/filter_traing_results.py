import csv

item_cat = {}


def is_item_same_category(item1_id, item2_id):
    item1_cat = item_cat[item1_id]
    item2_cat = item_cat[item2_id]
    if item1_cat == item2_cat:
        return True
    else:
        return False


with open("dim_items.txt") as items, open("similar_itmes_traing_result.csv") as traing_result, open("filter_result.csv",
                                                                                      "wb") as filter_result:
    for line in items:
        item_info = line.strip().split(" ")
        item_cat[item_info[0]] = item_info[1]
        # print(item_cat[item_info[0]])

    csv_writer = csv.writer(filter_result)
    title = ["item_id", "similar", "score", "rank"]
    csv_writer.writerow([x for x in title])

    next(traing_result)
    for line in traing_result:
        sim_info = line.strip().split(",")
        # print(sim_info)
        if is_item_same_category(sim_info[0], sim_info[1]):
            csv_writer.writerow([x for x in sim_info])
