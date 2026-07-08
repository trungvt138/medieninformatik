import pandas as pd

data = pd.read_csv('dataset.csv', index_col='tag')
print(data.columns)

new_samples = pd.Series([{
    'outlook': 'sunny',
    'temperature': 'mild',
    'humidity': 'normal',
    'windy': False
    }, 
    {'outlook': 'sunny',
    'temperature': 'mild',
    'humidity': 'normal',
    'windy': True
    },
    {'outlook': 'rainy',
    'temperature': 'hot',
    'humidity': 'normal',
    'windy': False
    }])

features = data.columns[0:-1]

obj = new_samples[1]

k = 4

def hamming_distance(obj, row, features):
    return sum([row[f] != obj[f] for f in features])

distances = []
for index, row in data.iterrows():
    d = hamming_distance(obj, row, features)
    distances.append((d, index))

distances_sorted = sorted(distances)   # mặc định sort theo phần tử đầu tiên của tuple, tức là distance
k_nearest = distances_sorted[:k]        # lấy k phần tử đầu

print(k_nearest)

sailing_values = [data.loc[index, 'sailing'] for distance, index in k_nearest]
print(sailing_values)

result = sailing_values.count('yes') > sailing_values.count('no')
print(result)