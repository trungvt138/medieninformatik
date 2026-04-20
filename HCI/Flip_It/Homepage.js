import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, TextInput, TouchableOpacity } from 'react-native';
import { Image } from 'react-native';

export default function Homepage() {
  return (
    <View style={styles.container}>
      <TextInput style={styles.searchBox} placeholder="Search..." />
      <View style={styles.horizontalRuler} />
      {/* <Text style={styles.text}>Welcome to FlipIt!</Text> */}
      {/* <StatusBar style="auto" /> */}
      <View style={styles.plusCircleView}>
        <TouchableOpacity activeOpacity={0.6}>
          <Image source={require('./assets/app/plus-circle.png')} style={styles.plusCircle} />
        </TouchableOpacity>
      </View>
    </View>
  );
}

const styles = StyleSheet.create({
  container: {
    flex: 1,
    backgroundColor: '#fff',
    justifyContent: 'flex-start',
    paddingTop: 32,
  },
  text: {
    fontSize: 24,
    fontWeight: 'bold',
  },
  searchBox: {
    alignSelf: 'stretch',
    height: 36,
    alignItems: 'center',
    backgroundColor: '#D9D9D9',
    marginHorizontal: 30,
    borderRadius: 8,
    paddingLeft: 13,
    paddingTop: 10,
    paddingBottom: 9,
    textAlignVertical: 'center',
  },
  horizontalRuler: {
    borderBottomColor: 'black',
    borderBottomWidth: StyleSheet.hairlineWidth,
    paddingTop: 28
  },
  plusCircleView: {
    paddingTop: 212
  },
  plusCircle: {
    width: 150,
    height: 150,
    alignSelf: 'center'
  },
});
