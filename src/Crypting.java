//public class Crypting {
//    public static String GenerateUniqueRandomToken()
//    // generates a unique, random, and alphanumeric token
//    {
//            const String availableChars =
//            "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz";
//        using (var generator = new RNGCryptoServiceProvider())
//        {
//            var bytes = new byte[16];
//            generator.GetBytes(bytes);
//            var chars = bytes
//                    .Select(b => availableChars[b % availableChars.Length]);
//            var token = new string(chars.ToArray());
//            return token;
//        }
//    }
//}
