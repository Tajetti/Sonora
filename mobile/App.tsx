import { StatusBar } from 'expo-status-bar';
import { StyleSheet, Text, View } from 'react-native';

export default function App() {
  return (
    <View style={styles.header}>

    </View>
  );
}

const styles = StyleSheet.create({
  header: { //header com config na esquerda e a foto de perfil na direita
    flex: 1,
    backgroundColor: '#fff',
  }
});
