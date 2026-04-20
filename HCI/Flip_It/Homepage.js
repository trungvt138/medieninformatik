import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View, TextInput } from 'react-native';

export default function Homepage() {
  return (
    <View style={styles.container}>
      <TextInput style={styles.searchBox} placeholder="Search..." />
      <View style={styles.horizontalRuler} />
      {/* <Text style={styles.text}>Welcome to FlipIt!</Text> */}
      {/* <StatusBar style="auto" /> */}
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
  }
});
