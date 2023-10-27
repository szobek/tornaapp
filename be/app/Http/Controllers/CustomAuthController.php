<?php
namespace App\Http\Controllers;
use Illuminate\Http\Request;
use Hash;
use Session;
use App\Models\User;
use Illuminate\Support\Facades\Auth;
use Illuminate\Support\Facades\DB;

class CustomAuthController extends Controller
{
    public function index()
    {
        return view('auth.login');
    }  
    public function loginWithFronend(Request $request){
        $credentials = $request->validate([
            'email' => ['required', 'email'],
            'password' => ['required'],
        ]);
 
        if (Auth::attempt($credentials)) {
            $request->session()->regenerate();

            $name = DB::table('user_data')
            ->join('users', 'users.id', '=', 'user_data.user_id')
            ->select('first_name', 'phone', 'last_name')
            ->get();

 //$name = DB::table("user_data")->where('user_id','SELECT id FROM users WHERE email="kunszt.norbert@gmail.com"')->select(['first_name','last_name'])->get();
 //print_r($name);
            return json_encode($name);
        }
 
        return json_encode("error");
    }
      

    public function registration()
    {
        return view('auth.registration');
    }
      
    public function customRegistration(Request $request)
    {  
        $request->validate([
            'name' => 'required',
            'email' => 'required|email|unique:users',
            'password' => 'required|min:6',
        ]);
           
        $data = $request->all();
        $check = $this->create($data);
         
        return redirect("dashboard")->withSuccess('You have signed-in');
    }

    public function dashboard()
    {
        if(Auth::check()){
            return view('dashboard');
        }
  
        return redirect("login")->withSuccess('You are not allowed to access');
    }
    
    public function signOut() {
        Session::flush();
        Auth::logout();
  
        return Redirect('login');
    }
}